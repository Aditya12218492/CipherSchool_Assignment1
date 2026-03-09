import http from 'k6/http';
import { check } from 'k6';

export const options = {
  stages: [
    { duration: '10s', target: 20 }, // ramp up
    { duration: '30s', target: 20 }, // sustain
    { duration: '10s', target: 0 },  // ramp down
  ],

  thresholds: {
    http_req_duration: ['p(95)<2000'], // 95% requests under 2s
    http_req_failed: ['rate<0.01'],    // error rate <1%
  },
};

export default function () {

  const res = http.get('https://api.practicesoftwaretesting.com/products/search?q=hammer');

  check(res, {
    'status is 200': (r) => r.status === 200,
  });

}