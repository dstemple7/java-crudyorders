package com.lambda.orders.services;

import com.lambda.orders.models.Agent;
import com.lambda.orders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices
{
    @Autowired
    AgentsRepository agentrepos;

    @Override
    public Agent findAgentById(long id)
    {
        return agentrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found!"));
    }

    @Override
    public Agent save(Agent agent){
        return agentrepos.save(agent);
    }
}
