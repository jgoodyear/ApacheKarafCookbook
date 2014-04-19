package com.packt;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.vmOption;
import static org.ops4j.pax.exam.CoreOptions.streamBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;

import java.io.File;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel;
import org.ops4j.pax.exam.options.extra.VMOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

import com.packt.impl.CalculatorImpl;

@ExamReactorStrategy(PerClass.class)
@RunWith(PaxExam.class)
public class TestKaraf {
	
    protected static final String COVERAGE_COMMAND = "coverage.command";

	@Inject
	protected BundleContext bundleContext;

	@Inject
	protected Calculator calculator;
	
	@Configuration
	public static Option[] configuration() throws Exception {
		return new Option[] {
				karafDistributionConfiguration().frameworkUrl(maven().groupId("org.apache.karaf").artifactId("apache-karaf")
						.type("zip").versionAsInProject())
						.unpackDirectory(new File("target/paxexam/unpack/"))
						.useDeployFolder(false),
				configureConsole().ignoreLocalConsole(),
				logLevel(LogLevel.INFO),
				keepRuntimeFolder(),
				addCodeCoverageOption(),
				streamBundle(
						bundle().add(Calculator.class)
								.add(CalculatorImpl.class)
								.add("OSGI-INF/blueprint/blueprint.xml",
										new File("src/main/resources/OSGI-INF/blueprint/blueprint.xml").toURL())
								.set(Constants.BUNDLE_SYMBOLICNAME,
										"com.packt.IntegrationTest")
								.set(Constants.DYNAMICIMPORT_PACKAGE, "*")
								.set(Constants.EXPORT_PACKAGE, "com.packt")
								.build()).start()
				};
	}

	private static Option addCodeCoverageOption() {
		String coverageCommand = System.getProperty(COVERAGE_COMMAND);
		if (coverageCommand != null) {
			System.out.println("Setting coverage command to: "+coverageCommand);
			return CoreOptions.vmOption(coverageCommand);
		}
		return null;
	}
	
	@Test
	public void test() {
		assertNotNull(bundleContext);
	}
	
	@Test
	public void testCalculatorAdd() throws Exception {
		double add = calculator.add(2.0, 2.5);
		assertThat(add, is(4.5));
	}
}
