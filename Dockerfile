FROM gradle:7.0.0-jdk16 AS build
COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle test && \
    gradle distTar

FROM openjdk:16-slim

COPY --from=build /home/gradle/src/build/distributions/*.tar /home/snug/cli.tar
RUN tar xfv /home/snug/cli.tar --directory=/home/snug/ && \
    rm /home/snug/cli.tar
RUN mv /home/snug/cli-* /home/snug/cli
ENV PATH="/home/snug/cli/bin:${PATH}"

RUN mkdir /data
WORKDIR /data

ENTRYPOINT ["cli"]


