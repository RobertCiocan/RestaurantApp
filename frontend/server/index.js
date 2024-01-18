const express = require("express");
const compression = require('compression');
const axios = require('axios');
const bodyParser = require("body-parser");

const port = process.env.PORT || 4200;
const gwAddress = process.env.GATEWAY_ADDRESS || "http://localhost:8080";

const sources = "../nu-restaurant/dist/nu-restaurant";
const options = {
  dotfiles: 'ignore',
  etag: false,
  extensions: ['html', 'js', 'scss', 'css'],
  index: false,
  maxAge: '1y',
  redirect: true,
}

const app = express();
app.use(compression());
app.use(bodyParser.json());
app.use(express.static(sources, options));

app.get('/api/*', (req, res) => {
    const resolvedPath = req.path.split('/').slice(2).join('/') 
    console.log(`Forwarding GET request to ${gwAddress}/${resolvedPath}`);
    axios.get(`${gwAddress}/${resolvedPath}`).then((response) => res.status(response.status).send(response.data))
    .catch(e => {
        if (e.code == 'ECONNREFUSED') {
            res.status(500).send(e.code);
            return;
        }
        console.log(`Request to ${gwAddress}${req.path} failed with status ${e.response.status}` )
        res.status(e.response.status).send(e.response.data)
    });
});

app.post('/api/*', (req, res) => {
    const resolvedPath = req.path.split('/').slice(2).join('/') 
    console.log(`Forwarding POST request to ${gwAddress}/${resolvedPath}`);
    axios.get(`${gwAddress}/${resolvedPath}`, req.body).then((response) => res.status(response.status).send(response.data))
    .catch(e => {
        if (e.code == 'ECONNREFUSED') {
            res.status(500).send(e.code);
            return;
        }
        console.log(`Request to ${gwAddress}${req.path} failed with status ${e.response.status}` )
        res.status(e.response.status).send(e.response.data)
    });
});

app.put('/api/*', (req, res) => {
    const resolvedPath = req.path.split('/').slice(2).join('/') 
    console.log(`Forwarding PUT request to ${gwAddress}/${resolvedPath}`);
    axios.put(`${gwAddress}/${resolvedPath}`, req.body).then((response) => res.status(response.status).send(response.data))
    .catch(e => {
        if (e.code == 'ECONNREFUSED') {
            res.status(500).send(e.code);
            return;
        }
        console.log(`Request to ${gwAddress}${req.path} failed with status ${e.response.status}` )
        res.status(e.response.status).send(e.response.data)
    });
});

app.patch('/api/*', (req, res) => {
    const resolvedPath = req.path.split('/').slice(2).join('/') 
    console.log(`Forwarding PATCH request to ${gwAddress}/${resolvedPath}`);
    axios.patch(`${gwAddress}/${resolvedPath}`, req.body).then((response) => res.status(response.status).send(response.data))
    .catch(e => {
        if (e.code == 'ECONNREFUSED') {
            res.status(500).send(e.code);
            return;
        }
        console.log(`Request to ${gwAddress}${req.path} failed with status ${e.response.status}` )
        res.status(e.response.status).send(e.response.data)
    });
});

app.delete('/api/*', (req, res) => {
    const resolvedPath = req.path.split('/').slice(2).join('/') 
    console.log(`Forwarding DELETE request to ${gwAddress}/${resolvedPath}`);
    axios.delete(`${gwAddress}/${resolvedPath}`).then((response) => res.status(response.status).send(response.data))
    .catch(e => {
        if (e.code == 'ECONNREFUSED') {
            res.status(500).send(e.code);
            return;
        }
        console.log(`Request to ${gwAddress}${req.path} failed with status ${e.response.status}` )
        res.status(e.response.status).send(e.response.data)
    });
});

app.all('*', function (req, res) {
    res.status(200).sendFile(`/`, {root: sources});
});

app.listen(port, () => {
  console.log(`Proxy server running at http://localhost:${port} forwarding to ${gwAddress}`);
});