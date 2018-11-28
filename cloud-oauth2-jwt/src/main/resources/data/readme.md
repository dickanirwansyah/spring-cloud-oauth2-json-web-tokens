# 1. buat keystore file

`keytool -genkeypair -alias jwt -keyalg RSA -keypass password -keystore jwt.jks -storepass password`

# 2. lakukan ke migrate pkcs12 (ini rekomendasi dari keytool)

`keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12`

# 3. export public key

`keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey`

# 4. copy file jwt.jks ke file /resource/

# 5. Jika berhasil maka ouputnya seperti ini

`-----BEGIN PUBLIC KEY-----
 MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApMrooeb2QGEjSsSfblti
 Pr7/38PXpOfRZVGTaJAKiG0MjDCMKrN6s5icLShtE9XV5JUY1Begoxc9fr6nrrRc
 afGFb+6Br79K6Zdu6RXFGHA3FeT7obMCnToFrCtMYzoKPlGMZh9rMbNJUZ0ab3u+
 rDoQNO8GZA05U5ek5xLvCoFkNs18SKmbDU88sdlKyzZy11v/K2nyLU7CRCuWdWgk
 P97ptFb7gk77sKoW0bNHxaFuZ2flSuIahyWW/F+gAzzIqtVdAf4nfvLLIUbNHVyZ
 QOI/Lnio+lDmYWkKJGomIyKUn49W4cmxgudRWNWB5hOoTsr90MeesMPtJPagWsy/
 bQIDAQAB
 -----END PUBLIC KEY-----
 -----BEGIN CERTIFICATE-----
 MIIDkTCCAnmgAwIBAgIEAVkLzzANBgkqhkiG9w0BAQsFADB5MQswCQYDVQQGEwJJ
 RDENMAsGA1UECBMEamF3YTEQMA4GA1UEBxMHamFrYXJ0YTEaMBgGA1UEChMRc29m
 dHdhcmUgZW5naW5lZXIxEjAQBgNVBAsTCWZ1c2kyNGNvbTEZMBcGA1UEAxMQZGlj
 a2Egbmlyd2Fuc3lhaDAeFw0xODExMjYxNTQ4MDVaFw0xOTAyMjQxNTQ4MDVaMHkx
 CzAJBgNVBAYTAklEMQ0wCwYDVQQIEwRqYXdhMRAwDgYDVQQHEwdqYWthcnRhMRow
 GAYDVQQKExFzb2Z0d2FyZSBlbmdpbmVlcjESMBAGA1UECxMJZnVzaTI0Y29tMRkw
 FwYDVQQDExBkaWNrYSBuaXJ3YW5zeWFoMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A
 MIIBCgKCAQEApMrooeb2QGEjSsSfbltiPr7/38PXpOfRZVGTaJAKiG0MjDCMKrN6
 s5icLShtE9XV5JUY1Begoxc9fr6nrrRcafGFb+6Br79K6Zdu6RXFGHA3FeT7obMC
 nToFrCtMYzoKPlGMZh9rMbNJUZ0ab3u+rDoQNO8GZA05U5ek5xLvCoFkNs18SKmb
 DU88sdlKyzZy11v/K2nyLU7CRCuWdWgkP97ptFb7gk77sKoW0bNHxaFuZ2flSuIa
 hyWW/F+gAzzIqtVdAf4nfvLLIUbNHVyZQOI/Lnio+lDmYWkKJGomIyKUn49W4cmx
 gudRWNWB5hOoTsr90MeesMPtJPagWsy/bQIDAQABoyEwHzAdBgNVHQ4EFgQUdbx+
 g5rF0VdIVde7g/86TRm79fIwDQYJKoZIhvcNAQELBQADggEBAGL9l4Ocu/LXNvR2
 +L7e+khvLA3Dd7nBLtpScDNUE7HxXRTxKFn3vMq+/GA2nWHQTgKwZw7RSvK/2zWp
 0tyW9nZbcIANbyijIMM2OEWAVnzNHOq1D+vue1WrPXvRO+8Y1ZRX1cQ2X0Iwm/3U
 qkuCzYfu5raBmrKC7WhdZpyd0W+IVPKvsMIRncBD6Q/kYnEcWDGv0mucnYF3qnZI
 VD7HY/84d73qYQFmhq9QDFhcPFdpykOUalpPH1FsrUPVdRjdNh+zRdkZNwCpv6kp
 I4A9niZqM0S6CodbuYJ1ZyPtdYjLpTEfCspc1YPB6xOClWtPPvnU3TT6mO+4jOue
 cWFcnHU=
 -----END CERTIFICATE-----
`

# 6. copy public key ke semua resource yang membutuhkan

# 7. cara mendapatkan token

`curl -X POST -vu USER_CLIENT_APP:password 'http://localhost:9001/oauth/token?username=admin&password=password&grant_type=password'`

# 8. build spring boot docker images

`sudo docker build -t cloud-oauth2 .`