package com.mediatype.examplework.actor;

import akka.actor.AbstractActor;
import akka.actor.UntypedAbstractActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SaveActor extends AbstractActor { // TODO LOOK ASYNCHRONOUS ACCESS TO DATABASE WITH AKKA ACTOR LIBRARY

    @Override
    public Receive createReceive() {
        return null;
    }
}
