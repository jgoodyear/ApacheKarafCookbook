package com.packt;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.felix.service.command.CommandProcessor;
import org.apache.felix.service.command.CommandSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.ProbeBuilder;
import org.ops4j.pax.exam.TestProbeBuilder;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

@ExamReactorStrategy(PerClass.class)
@RunWith(PaxExam.class)
public class TestKaraf {

	@Inject
	protected BundleContext bundleContext;
	
	@Inject
	protected CommandProcessor commandProcessor;
	
    ExecutorService executor = Executors.newCachedThreadPool();

	@Configuration
	public static Option[] configuration() throws Exception {
		return new Option[] {
				karafDistributionConfiguration().frameworkUrl(maven().groupId("org.apache.karaf").artifactId("apache-karaf")
						.type("zip").versionAsInProject())
						.unpackDirectory(new File("target/paxexam/unpack/"))
						.useDeployFolder(false),
				configureConsole().ignoreLocalConsole(),
				logLevel(LogLevel.INFO),
				keepRuntimeFolder()
				};
	}

	@ProbeBuilder
	public TestProbeBuilder probeConfiguration(TestProbeBuilder probe) {
		//make sure the needed imports are there. 
		probe.setHeader(Constants.DYNAMICIMPORT_PACKAGE, "*,org.apache.felix.service.*;status=provisional");
		return probe;
	}
	
	@Test
	public void test() {
		assertNotNull(bundleContext);
	}
	
	@Test
	public void testCommand() {
		String executeCommand = executeCommand("bundle:list");
		System.err.println(executeCommand);
		assertNotNull(executeCommand);
		assertThat(executeCommand, containsString("START LEVEL 100"));
	}
	
	
	/**
	 * See also the CellarTestSupport class of the Apache Karaf Cellar project. 
	 * 
     * Executes a shell command and returns output as a String.
     * Commands have a default timeout of 10 seconds.
     *
     * @param command The command to execute.
     * @return
     */
    protected String executeCommand(final String command) {
        String response;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final PrintStream printStream = new PrintStream(byteArrayOutputStream);
        final CommandSession commandSession = commandProcessor.createSession(System.in, printStream, System.err);
        FutureTask<String> commandFuture = new FutureTask<String>(
                new Callable<String>() {
                    public String call() {
                        try {
                            System.err.println(command);
                            commandSession.execute(command);
                        } catch (Exception e) {
                            e.printStackTrace(System.err);
                        }
                        printStream.flush();
                        return byteArrayOutputStream.toString();
                    }
                });

        try {
            executor.submit(commandFuture);
            response = commandFuture.get(10000L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            response = "SHELL COMMAND TIMED OUT: ";
        }

        return response;
    }
}
