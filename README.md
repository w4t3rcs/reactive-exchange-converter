<h1>Reactive Exchange Converter</h1>
<h3>This is an application for converting exchanges using another APIs. It is a test tech task for applying to Itembase company. If you want to see more about the project look at the <a href="#documentation">Documentation</a></h3>
<hr>
<br>
<h2 id="documentation">Documentation</h2>
<h3>REST API Endpoints</h3>
<h5>The only one endpoint of this application is <strong>"/api/v1.0/currency/convert"</strong>. It consumes and produces JSON. The body of your request looks like this: </h5>
<code>"{"from": "EUR","to": "USD", "amount": 23}"</code>
<h5>And the response looks like: </h5>
<code>"{"from": "EUR","to": "USD","amount": 23.0,"converted": 24.53}"</code>
<br><br>
<h3>Services</h3>
<h5>ProviderWebParser is for getting data from other APIs using WebClient and parsing to Mono of ProviderResponse</h5>
<h5>ProviderEndpointFormatter is for getting needed uri for API from ConversionRequest</h5>
<h5>ConverterService is for getting random provider and executing parsing (ProvideWebParser). Also it is the only one Service that is declared in ConversionController</h5>
<br>
<h3>Cache</h3>
<h5>Cache is implemented by Spring Data Redis (Containing Lettuce)</h5>
