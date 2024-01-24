<template>
  <div class="bg-light min-vh-100 d-flex flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol :md="9" :lg="7" :xl="6">
          <CCard class="mx-4">
            <CCardBody class="p-4">
              <CForm>
                <h1>User : Add</h1>
                <p class="text-medium-emphasis">Add a User</p>
                <CInputGroup class="mb-3">
                  <CInputGroupText>
                    <CIcon icon="cil-user" />
                  </CInputGroupText>
                  <CFormInput placeholder="Id" autocomplete="id" v-model="form.id" />
                </CInputGroup>
                <CInputGroup class="mb-3">
                  <CInputGroupText>@</CInputGroupText>
                  <CFormInput placeholder="Username" autocomplete="username"  v-model="form.username"/>
                </CInputGroup>
                <CInputGroup class="mb-3">
                  <CInputGroupText>
                    <CIcon icon="cil-lock-locked" />
                  </CInputGroupText>
                  <CFormInput
                  type="password"
                    placeholder="Password"
                    autocomplete="new-password"
                    v-model="form.password"
                  />
                </CInputGroup>
                <CInputGroup class="mb-4">
                  <CInputGroupText>
                    <CIcon icon="cil-address-book" />
                  </CInputGroupText>
                  <CFormInput
                    placeholder="Address"
                    autocomplete="address"
                    v-model="form.address"
                  />
                </CInputGroup>
                <div class="d-grid">
                  <CButton color="success" @click="addUser()">Add</CButton>
                </div>
              </CForm>
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>
    </CContainer>
  </div>

  <CModal :backdrop="true" :visible="modalValue" @close=" modalValue = false">
  <CModalHeader>
    <CModalTitle>{{this.modalTitle}}</CModalTitle>
  </CModalHeader>
  <CModalBody>{{ this.modalBody}}</CModalBody>
  <CModalFooter>
    <CButton color="secondary" @click="modalValue = false">Close</CButton>
  </CModalFooter>
</CModal>
</template>

<script>
export default {
  name: 'AddUser',
  data(){return{form:{     id:0,
 username:"",
  address:"",
 publicKey:"",
privateKey:"",
   password:""}, 
modalValue:false,
          modalTitle:"",
          modalBody:""}},
   methods:{ addUser(){
    console.log()
      
      this.$axios
      .post('http://localhost:8082/user/',this.form)
      .then((response) => {
          if (response.status == 200) {
            //this.tableExample = response.data.map(x => x.username) 

          // console.log(  this.tableExample)

          this.modalValue=true
          this.modalTitle="Add User Successful"
          this.modalBody="User Added Successful"
          }
        })
        .catch((error) => {
          console.log(error)
          this.modalValue=true
          this.modalTitle="Add User Unsuccessful"
          this.modalBody="User could not be added."
        })}}
}
</script>
