import axios from 'axios';

const basePath = "http://localhost:8080/api/1.0";

export default {

  saveMockAPI: (mockName, dataSchema, endPoint) => {
    return axios.post(basePath + `/createmock`, {mockName, dataSchema, endPoint});
  }

}