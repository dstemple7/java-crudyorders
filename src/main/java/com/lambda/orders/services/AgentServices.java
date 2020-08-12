package com.lambda.orders.services;

import com.lambda.orders.models.Agent;

public interface AgentServices
{

    Agent findAgentById(long id);

    Agent save(Agent agent);

}
