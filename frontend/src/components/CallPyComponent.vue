<template>
  <div class="callPy">
    <img id="displayedImage" alt="" src=""/>
    <hr class="logo">
    调用算法
    <hr>
    <input type="file" id="file" hidden @change="fileChange">
    <div class="input-with-button">
      <input name="pathInput" type="text" v-model="imagePath" placeholder="请输入文件路径"/>
      <button @click="btnChange">在本地选择</button>
    </div>
    <button name="callPyButton" @click="callPy">调用</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import {getDataForTable} from '../utils/utils.js';

const imagePath = ref('');
const pyResponse = ref('');

const showImg = () => {
  axios.post('http://localhost:8080/CadDet/getResultImg')
    .then(response => {
        console.log(response);
        document.getElementById('displayedImage').src = response.data;
    })
    .catch(error => {
      console.error(error);
      alert('Failed to load image.');
    });
};

const callPy = () => {
  axios.post('http://localhost:8080/CadDet/CallPy', {
    path: `${imagePath.value}`
  }).then(response => {
    console.log(response);
    pyResponse.value = JSON.stringify(response.data);
    getDataForTable();
    showImg();
  }).catch(error => {
    console.error(error.response.data);
    alert(error.response.data);
  });
};

const fileChange = (e) => {
  const file = e.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (event) => {
      document.getElementById('displayedImage').src = event.target.result;
    };
    reader.readAsDataURL(file);
  }
};

const btnChange = () => {
  const file = document.getElementById('file');
  file.click();
};
</script>

<style scoped>

img {
  max-width: 100%; /* 调整图片的最大宽度，确保它不会超出其容器的宽度 */
  height: auto; /* 保持图片的原始宽高比 */
  display: block; /* 确保图片不会有额外的空间 */
  margin: 50px auto; /* 图片居中显示 */
}

input {
  width: 80%; /* 输入框的宽度为容器的100% */
  padding: 10px; /* 输入框的内边距 */
  margin-bottom: 10px; /* 在下方添加一些空间 */
  border: 1px solid #d0d0d0; /* 设置文本框边框的颜色为更浅的灰色 */
}

button {
  width: 40%; /* 按钮的宽度为容器的100% */
  margin-top: 5px; /* 在上方添加一些空间 */
}

.logo {
  margin-top: 50px; /* 在顶部添加一些空间 */
}

</style>