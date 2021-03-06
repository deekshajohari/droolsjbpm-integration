/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.server.client.helper;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.TaskAssigningRuntimeClient;
import org.kie.server.client.impl.TaskAssigningRuntimeClientImpl;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.kie.server.api.KieServerConstants.CAPABILITY_TASK_ASSIGNING_RUNTIME;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskAssigningRuntimeServicesClientBuilderTest {

    @Mock
    private KieServicesConfiguration configuration;

    @Mock
    private ClassLoader classLoader;

    private TaskAssigningRuntimeServicesClientBuilder clientBuilder;

    @Before
    public void setUp() {
        when(configuration.getMarshallingFormat()).thenReturn(MarshallingFormat.XSTREAM);
        clientBuilder = new TaskAssigningRuntimeServicesClientBuilder();
    }

    @Test
    public void getImplementedCapability() {
        assertEquals(CAPABILITY_TASK_ASSIGNING_RUNTIME, clientBuilder.getImplementedCapability());
    }

    @Test
    public void build() {
        Map<Class<?>, Object> result = clientBuilder.build(configuration, classLoader);
        assertTrue(result.get(TaskAssigningRuntimeClient.class) instanceof TaskAssigningRuntimeClientImpl);
    }
}
