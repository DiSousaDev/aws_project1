FROM openjdk:11
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-Dspring.profiles.active=dev","-cp","app:app/lib/*","br.dev.diego.aws_project1.AwsProject1Application"]