#!/usr/bin/env sh
set -eu

envsubst '${hotels_service_url_internal} ${bookings_service_url_internal} ${api_gateway_key}' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf

exec "$@"