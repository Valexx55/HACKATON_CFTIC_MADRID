import requests
from requests.exceptions import HTTPError
# EJEMPLO PETICIÓN API CENTROS DE DÍA
api_url = "https://datos.madrid.es/egob/catalogo/200342-0-centros-dia.json"
try:
    response = requests.get(api_url)
    response.raise_for_status()
    # access JSOn content
    jsonResponse = response.json()
    for i in jsonResponse['@graph']:
        print(i['title'])

except HTTPError as http_err:
    print(f'HTTP error occurred: {http_err}')
except Exception as err:
    print(f'Other error occurred: {err}')
"""--------------------------------------------------------------------"""
# EJEMPLO PETICIÓN API CENTROS DE DÍA POR DISTRITO
print('-----------------------------CENTROS DE DÍA EN EL RETIRO---------------------------------------')
import requests
from requests.exceptions import HTTPError
# EJEMPLO PETICIÓN API CENTROS DE DÍA
api_url = "https://datos.madrid.es/egob/catalogo/200342-0-centros-dia.json?distrito_nombre=RETIRO"
try:
    response = requests.get(api_url)
    response.raise_for_status()
    # access JSOn content
    jsonResponse = response.json()
    for i in jsonResponse['@graph']:
        print(i['title'])

except HTTPError as http_err:
    print(f'HTTP error occurred: {http_err}')
except Exception as err:
    print(f'Other error occurred: {err}')



