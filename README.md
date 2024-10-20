# PMC Location API

This Spigot plugin allows the server on which it is installed to communicate
the locations of all connected players through a **REST API** on port **7070**.
Below are the API endpoints and their respective responses.

## `GET` Player location

### URL

```bash
/api/location/{player}
```

### API Key

```http request
X-API-KEY: {api_key}
```

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
}
```

</td>
</tr>
</tbody>
</table>



