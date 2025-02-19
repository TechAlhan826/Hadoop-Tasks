# Hadoop MapReduce Tasks - Big Data Project 🚀
 
🌍 **Use Case:** Learning & Implementing Core Hadoop MapReduce Concepts  
📂 **Repository Purpose:** Educational, hands-on Hadoop examples for beginners  

---

## 📘 **Overview**
This repository demonstrates core **Hadoop MapReduce tasks** with detailed examples, step-by-step explanations, and ready-to-run JAR files. Whether you're exploring Hadoop for the first time or solidifying your knowledge, this project covers real-world big data scenarios like word counting, data sorting, temperature analysis, and inverted indexing.

---

## 🛠 **Prerequisites**

- **Hadoop 3.3.6 installed and configured**
- **Java JDK 8+ installed**
- **SSH configured and Hadoop services running**
- **Dataset uploaded to HDFS**

Start Hadoop Services:
```bash
sudo service ssh restart
sbin/start-all.sh
```

Verify Hadoop is Running:
```bash
jps
```

Expected processes: **NameNode, DataNode, ResourceManager, NodeManager**

---

## 🗃 **Project Structure**
```
/root/hadoop-project/
├── share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.6.jar
├── word-count/
├── inverted-index/
├── sorting/
├── mean-temp-calculation/
├── log-analysis/
├── MeanTemperature.java
├── InvertedIndex.java
├── txt-to-seq.py
└── generate-temp-data.py
```

---

## 🔥 **Implemented Tasks**

### 📊 **1. Word Count**
**What it does:** Counts the frequency of words in a large text file.

**Command:**
```bash
hadoop jar hadoop-mapreduce-examples-3.3.6.jar wordcount \
  /big-data/word-count/input.txt \
  /big-data/word-count/output
```

**Example Input:**
```
Hadoop is amazing.
Big data is the future.
```
**Example Output:**
```
Hadoop 1
is 2
amazing 1
```

### 📈 **2. Sorting (Handling Sequence Files)**
**What it does:** Sorts a large dataset of random numbers.

**Steps:**
1. **Generate Random Data (SequenceFile):**
```bash
hadoop jar hadoop-mapreduce-examples-3.3.6.jar randomtextwriter \
  /big-data/sorting/random_data.seq
```

2. **Perform Sorting:**
```bash
hadoop jar hadoop-mapreduce-examples-3.3.6.jar sort \
  /big-data/sorting/random_data.seq \
  /big-data/sorting/sorted_output
```

3. **Convert SequenceFile to Text:**
```bash
hdfs dfs -cat /big-data/sorting/sorted_output/part-r-00000 > sorted_numbers.txt
```

**Example Input:**
```
32220
11962
22549
```
**Example Output:**
```
11962
22549
32220
```

### 🌡 **3. Mean Temperature Calculation (Custom Java)**
**What it does:** Calculates the average temperature for each city.

**Example Input:**
```
2023-01-01,Mumbai,32.3
2023-01-01,Delhi,33.5
```
**Example Output:**
```
Mumbai 32.3
Delhi 33.5
```

**Compile & Run:**
```bash
javac -classpath `hadoop classpath` -d . MeanTemperature.java
jar cf mean-temp.jar MeanTemperature*.class
hadoop jar mean-temp.jar MeanTemperature \
  /big-data/mean-temp-calculation/temperature_data.txt \
  /big-data/mean-temp-calculation/output
```

### 🧠 **4. Inverted Index (Custom Java)**
**What it does:** Builds an index of words and the documents they appear in.

**Example Input:**
```
Doc1: Hadoop is powerful.
Doc2: Big data powers insights.
```
**Example Output:**
```
Hadoop Doc1
is Doc1
powerful Doc1
Big Doc2
data Doc2
```

**Compile & Run:**
```bash
javac -classpath `hadoop classpath` -d . InvertedIndex.java
jar cf inverted-index.jar InvertedIndex*.class
hadoop jar inverted-index.jar InvertedIndex \
  /big-data/inverted-index/input.txt \
  /big-data/inverted-index/output
```

---

## 📂 **File Organization**
All the necessary files for JAR generation (Java source code) and supporting scripts are available in this repository. You can compile and package them directly.

---

## 🧠 **Learning Takeaways**
- **WordCount & Grep tasks** can be handled by Hadoop's default JAR.
- **Sorting** requires converting text files to SequenceFiles for performance optimization.
- **Custom Java programs** are necessary for more advanced tasks (like temperature calculation & inverted index).
- **HDFS & Hadoop CLI mastery** is crucial for debugging and verifying results.

---

## 🔧 **Useful Commands**

📂 **Create Folders in HDFS:**
```bash
hdfs dfs -mkdir /big-data/sorting
```

📂 **Upload Files to HDFS:**
```bash
hdfs dfs -put local_file.txt /big-data/word-count/
```

🔍 **View HDFS Files:**
```bash
hdfs dfs -ls /big-data
```

📤 **Fetch Results:**
```bash
hdfs dfs -cat /big-data/word-count/output/part-r-00000
```

---

## 🚀 **Conclusion**
This repository is a comprehensive guide for learning Hadoop through practical tasks. It bridges the gap between theory and real-world big data scenarios, helping you master HDFS, MapReduce, and Java-based data processing.

📢 **If you find this useful, feel free to star the repo, fork it, and contribute!**

---

📩 **Contact Me:**  
🌐 **Website:** [techyalhan.in](https://techyalhan.in)  
🐦 **Instagram:** [@TechyAlhan](https://www.instagram.com/alhan_826)

---

