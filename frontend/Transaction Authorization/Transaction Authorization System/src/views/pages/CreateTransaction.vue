<template>
    <div class="bg-light min-vh-100 d-flex flex-row align-items-center">
      <CContainer>
        <CRow class="justify-content-center">
          <CCol :md="9" :lg="7" :xl="6">
            <CCard class="mx-4">
              <CCardBody class="p-4">
                <CForm>
                  <h1>Create Transaction</h1>
                  <CInputGroup class="mb-3">
                    <CFormInput placeholder="Amount" autocomplete="amount" v-model="form.amount" />
                  </CInputGroup>
                  <CInputGroup class="mb-3">
                    <CFormInput placeholder="Address" autocomplete="address"  v-model="form.address"/>
                  </CInputGroup>

                  <div class="d-grid">
                    <CButton color="success" @click="addTransaction()">Create</CButton>
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
    name: 'CreateTransaction',
    data(){return{form:{    
   address:"",
   user:"",
  amount:"",
   }, 
  modalValue:false,
            modalTitle:"",
            modalBody:""}},
     methods:{ addTransaction(){
        this.form.user= sessionStorage.getItem("user")
        this.$axios
        .post('http://localhost:8082/transaction/',this.form)
        .then((response) => {
            if (response.status == 200) {

  
            this.modalValue=true
            this.modalTitle="Transaction Success"
            this.modalBody="Transaction Recorded Successfully."
            }
          })
          .catch((error) => {
            console.log(error)
            this.modalValue=true
            this.modalTitle="Transaction Failed"
            this.modalBody=" Transaction Failed due to unauthorized address."
          })}}
  }
  </script>
  