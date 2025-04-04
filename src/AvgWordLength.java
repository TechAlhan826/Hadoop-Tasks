import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AvgWordLength {

    public static class LengthMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static Text avgKey = new Text("AVG_LENGTH");
        private IntWritable length = new IntWritable();

        public void map(Object key, Text value, Context context) 
                throws IOException, InterruptedException {
            String[] words = value.toString().split("\\s+");
            for (String w : words) {
                length.set(w.length());
                context.write(avgKey, length);
            }
        }
    }

    public static class LengthReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
        public void reduce(Text key, Iterable<IntWritable> values, Context context) 
                throws IOException, InterruptedException {
            int sum = 0, count = 0;
            for (IntWritable val : values) {
                sum += val.get();
                count++;
            }
            context.write(key, new DoubleWritable((double) sum / count));
        }
    }

    // THIS JAR FILE IS CODED AND COMPILED BY MOHAMMED ALHAN - 23BCA0173 - https://techyalhan.in
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Average Word Length Calculation");
        job.setJarByClass(AvgWordLength.class);
        job.setMapperClass(LengthMapper.class);
        job.setReducerClass(LengthReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
