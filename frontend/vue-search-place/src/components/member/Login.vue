<template>
  <div class="wrap text-center">
    <form class="form-signin" v-on:submit.prevent="onSubmit">
      <h1 class="h3 mb-3 font-weight-normal">로그인이 필요합니다.</h1>
      <div class="alert alert-danger" role="alert" v-if="errorState" >{{errorState}}</div>
      <label for="inputId" class="sr-only">아이디</label>
      <input type="text" id="inputId" name="userId" class="form-control" placeholder="아이디" v-model="userId" required />
      <label for="inputPassword" class="sr-only">비밀번호</label>
      <input
        type="password"
        id="inputPassword"
        name="userPassword"
        class="form-control"
        placeholder="비밀번호"
        v-model="userPassword"
        required
      />

      <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>

      <p class="mt-5 mb-3 text-muted">&copy;BeingApple</p>
    </form>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'Login',
  data () {
    return {
      userId: '',
      userPassword: ''
    }
  },
  methods: {
    ...mapActions(['login']),
    async onSubmit () {
      try {
        let loginResult = await this.login({userId: this.userId, userPassword: this.userPassword})
        if (loginResult) this.goToPages()
      } catch (err) {
        console.log(err)
      }
    },
    goToPages () {
      this.$router.push('/search')
    }
  },
  computed: {
    ...mapGetters({
      errorState: 'getErrorState'
    })
  }
}
</script>

<style scoped>
.wrap {
  height: 100%;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
