FROM openjdk:8-jre-slim
ENV JAVA_OPTS="-Xms256M -Xmx1024M"
COPY maven /maven/
WORKDIR /maven/Test_Job01
RUN chmod +x /maven/Test_Job01/Test_Job01_run.sh
RUN sed -i 's/ -Xms[0-9]*M//g' /maven/Test_Job01/Test_Job01_run.sh
RUN sed -i 's/-Xmx[0-9]*M/$JAVA_OPTS/g' /maven/Test_Job01/Test_Job01_run.sh
ENTRYPOINT ["./Test_Job01_run.sh"]
