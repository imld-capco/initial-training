FROM docker.elastic.co/beats/filebeat:7.10.2
COPY filebeat.yml /usr/share/filebeat/filebeat.yml
COPY certificate.crt /
COPY app.log /logs/app.log
USER root
RUN chown root:filebeat /usr/share/filebeat/filebeat.yml
USER filebeat