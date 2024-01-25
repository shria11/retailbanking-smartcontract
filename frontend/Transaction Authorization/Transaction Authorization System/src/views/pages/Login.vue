<template>
  <div class="bg-light min-vh-100 d-flex flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol :md="8">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <CForm @submit.prevent="login">
                  <h1>Transaction Authorization</h1>
                  <p class="text-medium-emphasis">Sign In to your account</p>
                  <CInputGroup class="mb-3">
                    <CInputGroupText>
                      <CIcon icon="cil-user" />
                    </CInputGroupText>
                    <CFormInput
                      placeholder="Username"
                      autocomplete="username"
                      v-model="form.username"
                    />
                  </CInputGroup>
                  <CInputGroup class="mb-4">
                    <CInputGroupText>
                      <CIcon icon="cil-lock-locked" />
                    </CInputGroupText>
                    <CFormInput
                      type="password"
                      placeholder="Password"
                      autocomplete="current-password"
                      v-model="form.password"
                    />
                  </CInputGroup>
                 
                  <CRow>
                    <CButton color="primary" type="submit" class="px-4"> Login </CButton>
                  </CRow>
                </CForm>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>            
      </CRow>
    </CContainer>
  </div>

  <CModal :backdrop="true" :visible="modalValue" @close=" modalValue = false">
  <CModalHeader>
    <CModalTitle>Login Failed</CModalTitle>
  </CModalHeader>
  <CModalBody>Username or password incorrect.</CModalBody>
  <CModalFooter>
    <CButton color="secondary" @click="modalValue = false">Close</CButton>
  </CModalFooter>
</CModal>
</template>

<script>
export default {
  name: 'Login',
  data(){
  return {
    form:{
      username:"",
password:"" },
modalValue:false,

}},
  methods: {
    login(){ 
      const params = new URLSearchParams();
      params.append("username", this.form.username)
      params.append("password", this.form.password)
      this.$axios
      .post('http://localhost:8082/user/authenticate',params)
      .then((response) => {
          if (response.status == 200) {
sessionStorage.setItem("user",this.form.username)
          this.$router.push({path: '/dashboard'})
          }
        })
        .catch((error) => {
          console.log(error)
          this.modalValue=true
        })}},
}
</script>
