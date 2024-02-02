#!/bin/bash

if [[ -z "${CAPITAL_USER_LOGIN}" || -z "${CAPITAL_USER_PASSWORD}" || -z "${CAPITAL_USER_APIKEY}" ]]; then
  if [ -f /vault/secrets/md.properties ]; then
    eval "$(sed -e '/^\s*$/d' -e 's/:\s*/=/' -e 's/[a-z].*=/\U&/' /vault/secrets/md.properties)"
    export CAPITAL_USER_PASSWORD=$(printf '%s\n' "$CAPITAL_USER_PASSWORD" | sed -e 's/[\/&]/\\&/g')
  else
    echo "There are no credentials. Exiting ..."
    exit 1
  fi
fi


exec java -server \
    -XX:ParallelGCThreads=4 \
    -Djava.net.preferIPv4Stack=true \
    -Xms1G \
    -Xmx1G \
    -XX:-OmitStackTraceInFastThrow -XX:+CreateCoredumpOnCrash -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/trading/hprof-dumps \
    -jar trading.jar $@
