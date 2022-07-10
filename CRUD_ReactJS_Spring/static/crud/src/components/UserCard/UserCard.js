import React from 'react';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPhone, faPen, faTrash } from '@fortawesome/free-solid-svg-icons';

import './UserCard.css';

function UserCard(props) {

  const editUser = () => { 
    // props.handleUpdateUser(props);
    console.log('pegar novas infos e atualizar');
  }

  const deleteUser = () => {
    if (window.confirm('Tem certeza que deseja deletar esse usuário?')) {
      props.handleDeleteUser(props.userId);
    } else {
      return;
    }
  }

  const contactListUser = () => {
    console.log('abrir modal lista de contato');
  }

  return (
    <div className='card__container'>      
      <div className='card__description'>
        <h2 className='card__description-text'>{ props.userName }</h2>
        <details>
          <summary>Detalhes:</summary>
          <p className='card__description-details'>Idade: { props.userAge }</p>
          <p className='card__description-details'>Lista de contato:</p>
          <p className='card__description-details-phoneNumber'>+55 (15) 99630-0177</p>
          <p className='card__description-details-phoneNumber'>+55 (15) 99630-0177</p>
          <p className='card__description-details-phoneNumber'>+55 (15) 99630-0177</p>
        </details>

      </div>
      
      <div className='card__actions'>
        <button className='card__actions-contact tooltip' onClick={contactListUser}>
          <FontAwesomeIcon icon={faPhone} />
          <span className='tooltiptext'>Lista de contato</span>
        </button>

        <button className='card__actions-edit tooltip' onClick={editUser}>
          <FontAwesomeIcon icon={faPen} />
          <span className='tooltiptext'>Editar usuário</span>
        </button>
        
        <button className='card__actions-delete tooltip' onClick={deleteUser}>
          <FontAwesomeIcon icon={faTrash} />
          <span className='tooltiptext'>Deletar usuário</span>
        </button>
      </div>
    </div>
  );
}

export default UserCard;