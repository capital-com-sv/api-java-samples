FROM openjdk:17.0.2

RUN mkdir -p /var/lib/trading/logs
RUN mkdir -p /var/log/trading/hprof-dumps


COPY target/api-java-samples-0.0.1-SNAPSHOT.jar /var/lib/trading/trading.jar
COPY bin/docker-entrypoint.sh /var/lib/trading/
COPY logstash-logback.xml  /var/lib/trading/

WORKDIR /var/lib/trading
#RUN curl https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.16.1/jmx_prometheus_javaagent-0.16.1.jar -o prometheus-agent.jar

RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["./docker-entrypoint.sh"]
