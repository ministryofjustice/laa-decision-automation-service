package com.laa.config;

import java.util.List;

import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.runtime.rule.FactHandle;


public class TrackingAgendaEventListener extends DefaultAgendaEventListener {



    public void matchCreated(MatchCreatedEvent event) {
    		
        System.out.println("Match created "+event);
    }

    public void afterMatchFired(AfterMatchFiredEvent event) {
       List<? extends FactHandle> factHandles = 	event.getMatch().getFactHandles();
       Rule rule  = event.getMatch().getRule();
       List<Object> objs = event.getMatch().getObjects();
        System.out.println("after match fired "+event);
    }

 
    public void beforeMatchFired(BeforeMatchFiredEvent event) {
        System.out.println("before match fired "+event);
    }
}
