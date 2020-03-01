FROM node:10.19.0-slim

WORKDIR /usr/local/src/mca/externalservice

COPY package.json ./
RUN npm i

COPY src ./src

EXPOSE 8082

ENTRYPOINT [ "node", "/usr/local/src/mca/externalservice/src/app.js" ]