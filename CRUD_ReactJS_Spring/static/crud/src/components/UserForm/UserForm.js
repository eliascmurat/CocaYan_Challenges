import './UserForm.css';

import React, { useState } from 'react';
import { Axios } from 'axios';

function UserForm(props) {
  const API = 'http://localhost:8080';
  const [values, setValues] = useState();

  const handleChangeValues = (value) => {
    setValues(prevValue => ({
      ...prevValue,
      [value.target.name]: value.target.value
    }));
  }

  const handleClickButton = () => { 
    if (validData()) {
      Axios.post(API + "/users", {
        userName: values.userName,
        userAge: values.userAge,
      }).then((response) => {
        if (response.status === 201) {
          props.handleAddUser({
            userId: response.data.userId,
            userName: response.data.userName,
            userAge: response.data.userAge,
          });
        } else {
          alert("❗ Ops... ocorreu um erro");
        }
      });
    }
  }

  const validData = () => { 
    if (!values) {
      alert("⚠️ Preencha todos os campos!");
      return false;
    }

    if (!values['userName']) {
      alert("⚠️ Nome do usuário não pode ser vazio!");
      return false;
    } else {
      if (values['userName'].length < 2 || values['userName'].length > 100) {
        alert("⚠️ Nome do usuário não pode ter menos que 2 caracteres e não pode ter mais de 100 caracteres!");
        return false;
      }
    }

    if (!values['userAge']) {
      alert("⚠️ Idade do usuário não pode ser vazio!");
      return false;
    } else {
      if (values['userAge'] < 1 || values['userAge'] > 150) {
        alert("⚠️ Idade do usuário inválida!");
        return false;
      }
    }

    return true;
  }

  return(
    <div className='form__container'>
      <h1 className='form__title'>Usuários</h1>

      <div className='form__control'>
        <input 
          type='text'
          name='userName'
          id='userName'
          maxLength={100}
          placeholder='Digite aqui...'
          className='form__input'
          onChange={handleChangeValues}
        />
        <label htmlFor='userName' className='form__label'>Nome do usuário:</label>
      </div>

      <div className='form__control'>
        <input 
          type='number'
          name='userAge'
          id='userAge'
          min="1" 
          max="150"
          placeholder='Digite aqui...'
          className='form__input'
          onChange={handleChangeValues}
        />
        <label htmlFor='userAge' className='form__label'>Idade do usuário:</label>
      </div>

      <button 
        className='form__button'
        onClick={handleClickButton}
      >
        Salvar
      </button>
    </div>
  );
}

export default UserForm;