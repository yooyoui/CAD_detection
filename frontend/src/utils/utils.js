import axios from 'axios';
import { ref } from 'vue';

export const detResult = ref([]);

export const getDataForTable = () => {
  axios.post('http://localhost:8080/CadDet/getDataForTable').then(response => {
    console.log(response);
    detResult.value = response.data.map(item => ({
      id: item.id,
      position: item.position,
      attribute: item.attribute,
    }));
  }).catch(error => {
    console.error(error);
    alert(error.response.data);
  });
};