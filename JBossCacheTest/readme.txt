<h1>JBoss_Custom_Cache_Test</h1>
<h2>Description :</h2>
<pre>
How to create and configure JBoss custom cache.

</pre>

<h2>Configure Step :</h2>

<pre>
1. Change jboss-cache-manager-jboss-beans.xml
<code>  $EAP_HOME/jboss-as/server/all/deploy/cluster/jboss-cache-manager.sar/META-INF/jboss-cache-manager-jboss-beans.xml</code> to <code>./resource/jboss-cache-manager-jboss-beans.xml</code>  
  
2. Deploy JBossCacheTest.war

3. Start 2 instance (profile = all)<br>
  <code> - $JBOSS_HOME/bin/run.sh -c all</code><br>  
  <code> - $JBOSS_HOME/bin/run.sh -c all -Djboss.service.binding.set=ports-02 -Djboss.messaging.ServerPeerID=4</code>
</pre>


<h2>Test Step :</h2>

<pre>
1. Go to URL :<a href="http://localhost:8080/JBossCacheTest/cache_form.html"> http://localhost:8080/JBossCacheTest/cache_form.html </a>.
 ==> Store Custom Cache Name: my-mvcc-shared, key:1, value:1
 ==> Submit

2. Go to URL :<a href="http://localhost:8280/JBossCacheTest/cache_form.html"> http://localhost:8080/JBossCacheTest/cache_form.html </a>.
 ==> Retrieve Custom Cache Name: my-mvcc-shared, key:1
 ==> Submit
 
3. Check if value is 1 or not 
</pre>


