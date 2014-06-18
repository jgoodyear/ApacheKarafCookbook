package com.packt.hadoop.demo.commands;

import com.packt.hadoop.demo.api.HdfsConfigService;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 * Displays the last log entries
 */
@Command(scope = "test", name = "storeconfigs", description = "store configurations in hdfs")
public class StoreConfigs extends OsgiCommandSupport {

    HdfsConfigService hdfsConfigService;

    protected Object doExecute() throws Exception {
        String KARAF_etc = System.getProperty("karaf.home") + "/etc";
        System.out.println("Copying files from " + KARAF_etc + " to " + HdfsConfigService.HDFS_LOCATION);
        hdfsConfigService.storeConfigs();

        return null;
    }

    public void setHdfsConfigService(HdfsConfigService hdfsConfigService) {
        this.hdfsConfigService = hdfsConfigService;
    }
}
