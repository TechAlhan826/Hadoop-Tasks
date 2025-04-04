import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SentenceLength {

    public static class SentenceMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text category = new Text();

        public void map(Object key, Text value, Context context) 
                throws IOException, InterruptedException {
            String sentence = value.toString();
            int wordCount = sentence.split("\\s+").length;
            
            if (wordCount <= 5) category.set("SHORT");
            else if (wordCount <= 10) category.set("MEDIUM");
            else category.set("LONG");
            
            context.write(category, one);
        }
    }

    public static class SentenceReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterable<IntWritable> values, Context context) 
                throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            context.write(key, new IntWritable(sum));
        }
    }

    // THIS JAR FILE IS CODED AND COMPILED BY MOHAMMED ALHAN - 23BCA0173 - https://techyalhan.in
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Sentence Length Categorization");
        job.setJarByClass(SentenceLength.class);
        job.setMapperClass(SentenceMapper.class);
        job.setReducerClass(SentenceReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
