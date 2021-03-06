/*
 * Copyright 2017 ThoughtWorks, Inc.
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

package cd.go.contrib.elasticagent.model;

import cd.go.contrib.elasticagent.Constants;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.internal.NodeOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationsImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class KubernetesClusterTest {
    @Test
    public void shouldCreateKubernetesClusterObject() throws Exception {
        final KubernetesClient kubernetesClient = mock(KubernetesClient.class);

        NodeOperationsImpl nodes = mock(NodeOperationsImpl.class);
        PodOperationsImpl pods = mock(PodOperationsImpl.class);

        when(nodes.list()).thenReturn(new NodeList());
        when(kubernetesClient.nodes()).thenReturn(nodes);

        when(pods.inNamespace(Constants.KUBERNETES_NAMESPACE_KEY)).thenReturn(pods);
        when(pods.list()).thenReturn(new PodList());
        when(kubernetesClient.pods()).thenReturn(pods);

        final KubernetesCluster cluster = new KubernetesCluster(kubernetesClient);

        verify(kubernetesClient, times(1)).nodes();
        verify(kubernetesClient, times(1)).pods();
    }
}