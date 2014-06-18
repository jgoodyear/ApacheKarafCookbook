package com.packt.hadoop.demo.hdfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;

import com.packt.hadoop.demo.api.HdfsConfigService;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsConfigServiceImpl implements HdfsConfigService {

    private final static String BASE_HDFS = "hdfs://localhost:9000";

    @Override
    public void storeConfigs() {
        String KARAF_etc = System.getProperty("karaf.home") + "/etc";
        Collection<File> files = FileUtils.listFiles(new File(KARAF_etc), new String[]{"cfg"}, false);

        for (File f : files) {
            System.out.println(f.getPath());

            ClassLoader tccl = Thread.currentThread().getContextClassLoader();
            try {
                Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
                String cfg = FileUtils.readFileToString(f);

                Path pt = new Path(BASE_HDFS + HDFS_LOCATION + "/" + f.getName());

                Configuration conf = new Configuration();
                conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
                conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());


                FileSystem fs = FileSystem.get(conf);

                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(fs.create(pt, true)));
                // TO append data to a file, use fs.append(Path f)

                br.write(cfg);
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Thread.currentThread().setContextClassLoader(tccl);
            }
        }
    }

    @Override
    public void readConfigs() {
        try {

            FileSystem fs = FileSystem.get(new Configuration());
            FileStatus[] status = fs.listStatus(new Path(BASE_HDFS + HDFS_LOCATION));
            for (int i = 0;i < status.length;i++) {
                BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(status[i].getPath())));
                String line;
                line = br.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = br.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {

    }

    public void destroy() {

    }
}
