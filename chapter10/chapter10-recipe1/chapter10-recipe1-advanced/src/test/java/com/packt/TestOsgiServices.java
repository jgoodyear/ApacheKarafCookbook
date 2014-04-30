package com.packt;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.cleanCaches;
import static org.ops4j.pax.exam.CoreOptions.frameworkProperty;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.streamBundle;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.CoreOptions.workingDirectory;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;
import static org.hamcrest.core.Is.is;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.ProbeBuilder;
import org.ops4j.pax.exam.TestProbeBuilder;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.packt.impl.CalcActivator;
import com.packt.impl.CalculatorImpl;

@ExamReactorStrategy(PerClass.class)
@RunWith(PaxExam.class)
public class TestOsgiServices {

	@Inject
	protected BundleContext bundleContext;

	@Inject
	protected Calculator calculator;

	@Configuration
	public static Option[] configuration() throws Exception {
		return options(
				workingDirectory("target/paxexam/"),
				cleanCaches(true),
				junitBundles(),
				frameworkProperty("osgi.console").value("6666"),
				frameworkProperty("osgi.console.enable.builtin").value("true"),
				frameworkProperty("felix.bootdelegation.implicit").value(
						"false"),
				systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level")
						.value("DEBUG"),
				streamBundle(
						bundle().add(Calculator.class)
								.add(CalculatorImpl.class)
								.add(CalcActivator.class)
								.set(Constants.BUNDLE_SYMBOLICNAME,
										"com.packt.IntegrationTest")
								.set(Constants.DYNAMICIMPORT_PACKAGE, "*")
								.set(Constants.BUNDLE_ACTIVATOR, CalcActivator.class.getName())
								.set(Constants.EXPORT_PACKAGE, "com.packt")
								.build()).start());
	}

	@Test
	public void test() {
		assertNotNull(bundleContext);
		assertNotNull(calculator);
	}

	@Test
	public void testCalculatorAdd() throws Exception {
		double add = calculator.add(2.0, 2.5);
		assertThat(add, is(4.5));
	}

}
