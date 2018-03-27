package com.digital.laa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.laa.config.KieConfig;
import com.laa.config.TrackingAgendaEventListener;
import com.laa.model.CaseFile;
import com.laa.model.MeansAssessment;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KieConfig.class)
public class LaaCcmsDecisionEngineApplicationTests {

	@Autowired
	private KieSession kieSession;
	@Autowired
	private KieContainer container;

	@Test
	public void testIncomeThreshold_over12000_meansRejected() {
		// Given
		CaseFile casefile = new CaseFile();
		
		MeansAssessment meansAssessment = new MeansAssessment();
		meansAssessment.setIncome(new BigDecimal(13000.00));

		casefile.setMeansAssessment(meansAssessment);
	
		AgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
		kieSession.addEventListener(agendaEventListener);
		
		kieSession.insert(casefile);

		kieSession.fireAllRules();
		
	
		// Then
		System.out.println("Status of means test is"+casefile.getMeansAssessment().getMeansAccepted());

		assertFalse(casefile.getMeansAssessment().getMeansAccepted());
	}
	
	

}
