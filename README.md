# PMC Location API

This Spigot plugin allows the server on which it is installed to communicate
the locations of all connected players through a **REST API** on port **7070**.

The plugin provides the in-game command `/requests` to see the count of requests 
sent to the API and their code.

## API Endpoint

The plugin gets the API key from the environment variables of the server.

<table>
<tbody>
<tr>
<td><strong>Method</strong></td>
<td>

`GET`

</td>
</tr>
<tr>
<td><strong>URL</strong></td>
<td>

```bash
/api/location/{player}
```

</td>
</tr>
<tr>
<td><strong>API Key</strong></td>
<td>

```http request
X-API-KEY: {api_key}
```

</td>
</tr>
</tbody>
</table>

### Success responses

<table>
<thead>
<tr>
<th>Code</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>

`200`

</td>
<td>

```json
{
    "world": "{world}",
    "x": {x},
    "y": {y},
    "z": {z}
}
```

</td>
</tr>
</tbody>
</table>

### Error responses

<table>
<thead>
<tr>
<th>Code</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>

`401`

</td>
<td>

`Invalid API Key`

</td>
</tr>
<tr>
<td>

`404`

</td>
<td>

`Player not found`

</td>
</tr>
</tbody>
</table>

### Example

<table>
<thead>
<tr>
<th>Request</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
<td>

```bash
curl -X GET \
     -H "X-API-Key: {api_key}" \
     http://{server_ip}:7070/api/location/Notch
```

</td>
<td>

```json
{
    "world": "world_nether",
    "x": 134,
    "y": 98,
    "z": 123
}
```

</td>
</tr>
</tbody>
</table>



