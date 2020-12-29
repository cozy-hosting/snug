FROM gradle:6.7.1-jdk15 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle distTar

FROM mcr.microsoft.com/java/jre:15-zulu-alpine
COPY --from=build /home/gradle/src/build/distributions/*.tar /home/snug/cli.tar
RUN tar xfv /home/snug/cli.tar --directory=/home/snug/ && \
    rm /home/snug/cli.tar
RUN mv /home/snug/cli-* /home/snug/cli
ENV PATH="/home/snug/cli/bin:${PATH}"
WORKDIR /home/snug/cli/bin
ENTRYPOINT ["cli"]


