<template>
  <div class="back">
    <img src="@/assets/image-logo.png" alt="logobee" width="100">
    <el-progress class="align-vertical" :percentage="percentage" :color="customColors" :format="format"></el-progress>

    <TestForm></TestForm>

    <button @click="finish">완료</button>

  </div>
</template>

<script>
import TestForm from "./TestForm";
import axios from "axios"

export default {
  name: "FeedWrite3",
  components: { TestForm,
  },
  data() {
    return {
      percentage: 80,
      customColors: [
        {color: '#5cb87a', percentage: 60},
        {color: '#1989fa', percentage: 80},
        {color: '#6f7ad3', percentage: 100}
      ],
      imgData: {
        files: [],
        postId: 0,
        thumbnail: [],
      }
    };
  },
  methods: {
    format(percentage) {
      if (this.percentage<100) {
        return percentage !== 100 ? '비잉 중' : `${percentage}%`;
      }
      else{
          percentage === 100 ? '비잉 완료' : `${percentage}%`;
          
        } 
        
    },
    finish() {
      this.percentage += 40;
      if (this.percentage > 100) {
        this.percentage = 100;
      }
      this.$store.state.postData.userId = localStorage.getItem('userId')
      console.log(this.$store.state.postData)
      // 우선 포스트를 만들고
      axios.post("http://127.0.0.1:8081/post", this.$store.state.postData)
      .then(res => {
        console.log(res)
        // 포트스 id를 작성해주고
        this.imgData.postId = res.data.postId
        // this.imgData.files = this.$store.state.files
        // this.imgData.thumbnail = this.$store.state.files[0]
        // console.log('썸네일타입')
        // console.log(typeof(this.$store.state.files[0]))
        const formData = new FormData()
        formData.append('thumbnail',this.$store.state.files[0])
        // const fileList = new FileList();
        for(var i = 0; i < this.$store.state.files.length; i++) {
          formData.append('files',this.$store.state.files[i])
        }
        // formData.append('files',this.$store.state.files)
        // console.log('이미지 데이터')
        // console.log(this.imgData)
        // console.log(formData.get('thumbnail'))
        // console.log(formData.get('files'))
        return axios.post("http://localhost:8081/post/postPic/"+this.imgData.postId,formData,{
        headers: {
          'Content-Type': 'multipart/form-data',
        }
})
       
      })
      .then(res => {
        console.log(res)
        this.$router.push("/feed");
      })
      .catch(err => {
        console.log(err)
      })
    },
  },

}
</script>

<style scoped>
.back {
  background-color: #ffe8c4;
  width: 375px;
  height: 812px;
  text-align: center;
}
</style>
