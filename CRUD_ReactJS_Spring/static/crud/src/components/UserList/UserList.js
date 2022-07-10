import './UserList.css';

import React from 'react';

import UserCard from '../UserCard/UserCard';
import { Axios } from 'axios';

function UserList(props) {
  const API = "http://localhost:8080";

  const handleUpdateUser = (userUpdate) => {     
    Axios.put(API + "/users/" + userUpdate.userId, {
      userName: userUpdate.userName,
      userAge: userUpdate.userAge,
    }).then((response) => {
      if (response.status === 200) {
        props.handleUpdateUser({
          userid: response.data.id,
          userName: response.data.userName,
          userAge: response.data.userAge,
        });
      } else {
        alert("❗ Ops... ocorreu um erro");
        return;
      }
    });  
  
  }

  const handleDeleteUser = (userId) => { 
    Axios.delete(API + "/users/" + userId).then((response) => {
      if (response.status === 200) {
        props.handleDeleteUser({ userDelete: userId });
      } else {
        alert("❗ Ops... ocorreu um erro");
      }
    });
  }

  return (
    <div className='listUsers__container'>
      { 
        props.listUsers.map((user) => {
          return (
            <UserCard 
              key={user.userId} 
              userId={user.userId}
              userName={user.userName}
              userAge={user.userAge}
              handleUpdateUser={handleUpdateUser}
              handleDeleteUser={handleDeleteUser}
            ></UserCard>
          );
        })
      }
    </div>
  );
}

export default UserList;