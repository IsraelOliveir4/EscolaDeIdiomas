import React from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';

const CadastroEstudante = () => {
  const validationSchema = Yup.object({
    cpf: Yup.string().required('CPF é obrigatório').length(11, 'CPF deve ter 11 caracteres'),
    nome: Yup.string().required('Nome é obrigatório'),
    sobrenome: Yup.string().required('Sobrenome é obrigatório'),
    telefone: Yup.string().required('Telefone é obrigatório'),
    whatsapp: Yup.string().required('WhatsApp é obrigatório'),
    email: Yup.string().email('Email inválido').required('E-mail é obrigatório'),
  });

  const handleSubmit = (values) => {
    axios.post('/api/estudantes', values)
      .then(response => {
        alert('Estudante cadastrado com sucesso!');
      })
      .catch(error => {
        console.error('Erro ao cadastrar estudante:', error);
      });
  };

  return (
    <div>
      <h1>Cadastro de Estudante</h1>
      <Formik
        initialValues={{
          cpf: '',
          nome: '',
          sobrenome: '',
          telefone: '',
          whatsapp: '',
          email: ''
        }}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
      >
        <Form>
          <div>
            <label>CPF:</label>
            <Field name="cpf" />
            <ErrorMessage name="cpf" component="div" />
          </div>

          <div>
            <label>Nome:</label>
            <Field name="nome" />
            <ErrorMessage name="nome" component="div" />
          </div>

          <div>
            <label>Sobrenome:</label>
            <Field name="sobrenome" />
            <ErrorMessage name="sobrenome" component="div" />
          </div>

          <div>
            <label>Telefone:</label>
            <Field name="telefone" />
            <ErrorMessage name="telefone" component="div" />
          </div>

          <div>
            <label>WhatsApp:</label>
            <Field name="whatsapp" />
            <ErrorMessage name="whatsapp" component="div" />
          </div>

          <div>
            <label>E-mail:</label>
            <Field name="email" />
            <ErrorMessage name="email" component="div" />
          </div>

          <button type="submit">Cadastrar</button>
        </Form>
      </Formik>
    </div>
  );
};

export default CadastroEstudante;
