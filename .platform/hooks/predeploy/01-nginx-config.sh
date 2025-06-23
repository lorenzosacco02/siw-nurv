#!/bin/bash

# Aggiungi client_max_body_size a nginx
echo "client_max_body_size 1024M;" > /etc/nginx/conf.d/client_max_body_size.conf

# Riavvia nginx per applicare la modifica
systemctl reload nginx
