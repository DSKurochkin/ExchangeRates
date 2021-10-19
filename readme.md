Creation of a service that accesses the service of exchange rates and sends a gif
in response. If the exchange rate against the ruble for today has become higher than yesterday,
then a random one with a tag "rich" is returned. Otherwise with the tag "broke".

API ENDPOINTS


- /alpha/codes - get object CurrencyCodeNameRespons with Map of all currency symbols, along with their full names

- /alpha/check - get object SimpleGif.  These objects contain a variety of information, which itself includes the URLS for multiple different GIFS formats and sizes

    Query Params: userCode:string. Сurrency code relative to which the ruble dynamics will be taken into account 

 