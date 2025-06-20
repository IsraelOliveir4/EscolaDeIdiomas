import React from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';

import { criarEstudante } from '../services/estudanteService';

const CadastroEstudante = () => {
  const validationSchema = Yup.object({
    cpf: Yup.string().required('CPF é obrigatório').length(11, 'CPF deve ter 11 caracteres'),
    nome: Yup.string().required('Nome é obrigatório'),
    sobrenome: Yup.string().required('Sobrenome é obrigatório'),
    telefone: Yup.string().required('Telefone é obrigatório'),
    whatsapp: Yup.string().required('WhatsApp é obrigatório'),
    email: Yup.string().email('Email inválido').required('E-mail é obrigatório'),
  });

  const handleSubmit = (values, { resetForm }) => {
    criarEstudante(values)
      .then(response => {
        if (response.success) {
          alert('Estudante cadastrado com sucesso!');
          resetForm(); // Limpa o formulário após sucesso
        } else {
          alert('Erro ao cadastrar estudante: ' + (response.error?.mensagem || response.error));
        }
      })
      .catch(error => {
        console.error('Erro ao cadastrar estudante:', error);
        alert('Erro ao cadastrar estudante!');
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
            <label>CPF *</label>
            <Field name="cpf" />
            <ErrorMessage name="cpf" component="div" style={{ color: 'red' }} />
          </div>

          <div>
            <label>Nome *</label>
            <Field name="nome" />
            <ErrorMessage name="nome" component="div" style={{ color: 'red' }} />
          </div>

          <div>
            <label>Sobrenome *</label>
            <Field name="sobrenome" />
            <ErrorMessage name="sobrenome" component="div" style={{ color: 'red' }} />
          </div>

          <div>
            <label>Telefone *</label>
            <Field name="telefone" />
            <ErrorMessage name="telefone" component="div" style={{ color: 'red' }} />
          </div>

          <div>
            <label>WhatsApp *</label>
            <Field name="whatsapp" />
            <ErrorMessage name="whatsapp" component="div" style={{ color: 'red' }} />
          </div>

          <div>
            <label>E-mail *</label>
            <Field name="email" />
            <ErrorMessage name="email" component="div" style={{ color: 'red' }} />
          </div>

          <button type="submit">Cadastrar</button>
        </Form>
      </Formik>
    </div>
  );
};

export default CadastroEstudante;
