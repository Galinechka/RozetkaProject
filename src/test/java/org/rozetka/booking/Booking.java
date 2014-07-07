package org.rozetka.booking;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

import java.text.SimpleDateFormat;
import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

import steps.RozetkaBookingSteps;

import org.apache.log4j.Logger;

@RunWith(JUnitReportingRunner.class)
public class Booking extends JUnitStories
{
	
	@Override
	public InjectableStepsFactory stepsFactory() {
		try {
			return new InstanceStepsFactory(configuration(), new RozetkaBookingSteps());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
 
	
	@Override
	public Configuration configuration() {
		Class<? extends Embeddable> embeddableClass = this.getClass();
		ParameterConverters parameterConverters = new ParameterConverters();
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
			new LocalizedKeywords(), new LoadFromClasspath(embeddableClass), parameterConverters);
		parameterConverters.addConverters(new DateConverter( new SimpleDateFormat("yyyy-MM-dd")),
			new ExamplesTableConverter(examplesTableFactory));
		return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(embeddableClass))
			.useStoryParser(new RegexStoryParser(examplesTableFactory))
			.useStoryReporterBuilder(new StoryReporterBuilder()
			.withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
			.withDefaultFormats().withFormats(CONSOLE, TXT, HTML, XML))
			.useParameterConverters(parameterConverters);
	}

	@Override
	public List<CandidateSteps> candidateSteps() {
		try {
			return new InstanceStepsFactory(configuration(), 
				new RozetkaBookingSteps()).createCandidateSteps();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(
			codeLocationFromClass(this.getClass()), "**/*.story", "**/excluded*.story");
	}

	@Test
	public void run() throws Throwable {
		
		super.run();
		
	}
	
	
}
