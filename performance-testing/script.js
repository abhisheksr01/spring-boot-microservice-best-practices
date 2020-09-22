import http from 'k6/http';
import { check, group, sleep } from "k6";

export default function () {
  let response = http.get(`http://${__ENV.MY_HOSTNAME}companieshouse/search/08240241`)
  check(response, {
      'is status 200': (r) => r.status === 200,
    });
}