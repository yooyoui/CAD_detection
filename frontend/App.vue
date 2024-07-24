<script setup>
import {ref, watch} from 'vue';
import axios from 'axios';

const imagePath = ref('');
const updateId = ref('');
const updatePosition = ref('');
const updateValue = ref('');
const deleteId = ref('');
const pyResponse = ref(''); // 用于存储调用Py接口的返回结果
const DBData = ref([]);
const detResult = ref([]);

const callPy = () => {
  axios.post('http://localhost:8080/CadDet/CallPy', {
    path: `--image_dir=${imagePath.value}`
  }).then(response => {
    console.log(response);
    pyResponse.value = JSON.stringify(response.data);
    getDataForTable();
  }).catch(error => {
    console.error(error.response.data);
    alert(error.response.data);
  });
};

const getDataForTable = () => {
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

const fetchImage = () => {
  axios.get(`http://localhost:8080/get-static-image`, {responseType: 'blob'})
      .then(response => {
        const imageBlob = new Blob([response.data], {type: 'image/jpeg'});
        document.getElementById('displayedImage').src = URL.createObjectURL(imageBlob);
      })
      .catch(error => console.error(error));
};

const createData = () => {
  axios.post('http://localhost:8080/CadDet/create').then(response => {
    console.log(response);
    alert(response.data)
  }).catch(error => {
    console.error(error);
    alert(error.response.data);
  });
};

const readData = () => {
  axios.get('http://localhost:8080/CadDet/read').then(response => {
    console.log(response.data);
    DBData.value = response.data.map(item => ({
      id: item.id,
      fileId: item.fileId,
      position: item.position,
      value: item.value,
    }));
  }).catch(error => {
    console.error(error);
    alert(error.response.data);
  });
};

const updateData = () => {
  axios.put(`http://localhost:8080/CadDet/update?id=${updateId.value}&position=${updatePosition.value}&value=${updateValue.value}`)
      .then(response => {
        console.log(response);
        alert(response.data)
      }).catch(error => {
    console.error(error);
    alert(error.response.data);
  });
};

const deleteData = () => {
  axios.delete(`http://localhost:8080/CadDet/delete?id=${deleteId.value}`).then(response => {
    console.log(response);
    alert(response.data)
  }).catch(error => {
    console.error(error);
    alert(error.response.data);
  });
}

const fileChange = (e) => {
  const file = e.target.files[0]; // 获取单个文件
  if (file) {
    const reader = new FileReader();
    reader.onload = (event) => {
      document.getElementById('displayedImage').src = event.target.result;
    };
    reader.readAsDataURL(file); // 读取文件内容，结果用于图片显示
  }
};

const btnChange = () => {
  const file = document.getElementById('file');
  file.click();
};

// 监听imagePath的变化
watch(imagePath, (newValue) => {
  updateImageSrc(newValue);
});

// 更新图片源的函数
const updateImageSrc = (path) => {
  const displayedImage = document.getElementById('displayedImage');
  if (displayedImage) {
    displayedImage.src = path;
  }
};


</script>

<template>
  <div class="flex-container">
    <div class="left-side">
      <div class="callPy">
        <img id="displayedImage" alt="" src=""/>
        <hr>
        调用算法
        <hr>
        <input type="file" id="file" hidden @change="fileChange">
        <div class="input-with-button">
          <input name="pathInput" type="text" v-model="imagePath" placeholder="请输入文件路径"/>
          <button @click="btnChange">在本地选择</button>
        </div>
        <button name="callPyButton" @click="callPy">调用</button>
      </div>

      <div class="update">
        更新数据
        <hr>
        <input name="updateIdInput" v-model="updateId" placeholder="请输入要更新的ID"/>
        <input name="updateContentInput" v-model="updatePosition" placeholder="请输入要更新的内容(位置)"/>
        <input name="updateContentInput" v-model="updateValue" placeholder="请输入要更新的内容(值)"/>
        <button name="updateButton" @click="updateData">更新</button>
      </div>

      <div class="delete">
        删除数据
        <hr>
        <input name="deleteIdInput" v-model="deleteId" placeholder="请输入要删除内容的ID"/>
        <button name="deleteDataButton" @click="deleteData">删除</button>
      </div>
    </div>

    <div class="right-side">
      <h1>
        算法运行结果
        <hr>
        <div class="displayedImageContainer">
          <img id="displayedImage" alt="" src=""/>
        </div>
        <span class="label-text">文本内容</span>
        <el-table :data="detResult" style="width: 100%">
          <el-table-column prop="id" label="ID"></el-table-column>
          <el-table-column prop="position" label="Position"></el-table-column>
          <el-table-column prop="attribute" label="Attribute"></el-table-column>
        </el-table>
        <button name="saveButton" @click="createData">保存结果</button>

        <br>
        <span class="label-text">读取数据库结果</span>
        <hr>
        <el-table :data="DBData" style="width: 100%">
          <el-table-column prop="id" label="ID"></el-table-column>
          <el-table-column prop="fileId" label="File ID"></el-table-column>
          <el-table-column prop="position" label="Position"></el-table-column>
          <el-table-column prop="value" label="Value"></el-table-column>
        </el-table>
        <button name="readButton" @click="readData">读取数据</button>
      </h1>


    </div>
  </div>
</template>


<style scoped>
/* App.vue 样式 */
.flex-container {
  display: flex; /* 使用Flexbox布局 */
  align-items: flex-start; /* 使子元素在交叉轴的开始位置对齐 */
  font-family: '黑体', sans-serif; /* 设置字体 */
  font-weight: bold;
}

.left-side {
  flex: 1; /* 左侧容器的宽度是右侧容器的一半 */
  max-width: 300px; /* 设置左侧容器的最大宽度，根据需要调整 */
  border-right: 1px solid #ccc; /* 在左侧容器的右侧添加一条线 */
  padding-right: 20px; /* 可选：在内容和边框之间添加一些空间 */
  margin-right: 250px; /* 在两个容器之间添加一些空间 */
}

.left-side input {
  width: 80%; /* 输入框的宽度为容器的100% */
  padding: 10px; /* 输入框的内边距 */
  margin-bottom: 10px; /* 在下方添加一些空间 */
  border: 1px solid #d0d0d0; /* 设置文本框边框的颜色为更浅的灰色 */
}

.left-side button {
  width: 40%; /* 按钮的宽度为容器的100% */
  margin-top: 5px; /* 在上方添加一些空间 */
}

.callPy {
  margin-top: 100px; /* 在上方添加一些空间 */
}

.update {
  margin-top: 50px; /* 在上方添加一些空间 */
}

.delete {
  margin-top: 50px; /* 在上方添加一些空间 */
}

.left-side hr {
  margin-top: 10px; /* 在<hr>标签上方添加一些空间 */
  border: 1px solid #d0d0d0; /* 设置横线的颜色为更浅的灰色 */
}

.right-side {
  flex: 2; /* 右侧容器的宽度是左侧容器的两倍 */
  max-width: 600px; /* 设置右侧容器的最大宽度，根据需要调整 */
}

.right-side {
  height: 100px;
  white-space: pre-wrap;
  overflow-wrap: break-word;
  margin-bottom: 5px; /* 在文本区域下方添加一些空间 */
  border: 1px solid #d0d0d0; /* 设置文本框边框的颜色为更浅的灰色 */
}

.displayedImageContainer img {
  max-width: 100%; /* 调整图片的最大宽度，确保它不会超出其容器的宽度 */
  height: auto; /* 保持图片的原始宽高比 */
  display: block; /* 确保图片不会有额外的空间 */
  margin: 50px auto; /* 图片居中显示 */
}

.right-side button {
  margin-bottom: 20px; /* 在按钮下方添加一些空间 */
}

.right-side hr {
  margin-top: 30px; /* 在<hr>标签上方添加一些空间 */
  margin-bottom: 30px; /* 在<hr>标签下方添加一些空间 */
  border: 1px solid #d0d0d0; /* 设置横线的颜色为更浅的灰色 */
}


h1 {
  width: 800px;
  margin: 100px 0;
  padding: 100px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #45a049;
}

input::placeholder {
  color: #a9a9a9;
}

.input-width {
  width: 100%; /* Adjust width as needed */
  height: 100px; /* Adjust height as needed */
  resize: both; /* Allow the user to resize both horizontally and vertically */
  white-space: pre-wrap; /* Ensure that whitespace is preserved and lines wrap */
  overflow-wrap: break-word; /* Ensure long words do not overflow */
}

#displayedImage {
  max-width: 100%; /* 设置缩略图的最大宽度 */
  max-height: 100%; /* 设置缩略图的最大高度 */
  object-fit: cover; /* 确保图片在调整大小时保持宽高比 */
}

.label-text {
  font-size: 24px; /* Adjust the font size as needed */
}

</style>
