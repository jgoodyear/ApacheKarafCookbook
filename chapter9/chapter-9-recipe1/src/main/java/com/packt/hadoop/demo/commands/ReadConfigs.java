package com.packt.hadoop.demo.commands;

import com.packt.hadoop.demo.api.HdfsConfigService;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 * Displays the last log entries
 */
@Command(scope = "test", name = "readconfigs", description = "Read configurations from HDFS")
public class ReadConfigs extends OsgiCommandSupport {

    private HdfsConfigService hdfsConfigService;

    protected Object doExecute() throws Exception {
        String KARAF_etc = System.getProperty("karaf.home") + "/etc";
                System.out.println("Copying files from hdfs:// " + HdfsConfigService.HDFS_LOCATION + " to "+  KARAF_etc  );
                hdfsConfigService.readConfigs();

        return null;
    }

    public void setHdfsConfigService(HdfsConfigService hdfsConfigService) {
        this.hdfsConfigService = hdfsConfigService;
    }
}
