import './App.css';

import { Axios } from 'axios';
import { useEffect, useState } from 'react';

import UserForm from './components/UserForm/UserForm';
import UserList from './components/UserList/UserList';

function App() {
  const API = 'http://localhost:8080';
  const [listUsers, setListUsers] = useState([
    {
      userId: 1,
      userName: 'Chrono',
      userAge: 17
    },
    {
      userId: 2,
      userName: 'Lucca',
      userAge: 19
    },
    {
      userId: 3,
      userName: 'Marle',
      userAge: 16
    }
  ]);

  // useEffect(() => {
  //   Axios.get(API + '/users').then((response) => {
  //     setListUsers(response.data);
  //   });
  // }, []);

  const handleAddUser = (newUser) => {
    const newListUser = [
      ...listUsers,
      {
        userId: newUser.userId,
        userName: newUser.userName,
        userAge: newUser.userAge
      }
    ];

    setListUsers(newListUser);
  }

  const handleUpdateUser = (userUpdate) => {
    listUsers.forEach(user => {
      if (user.userId === parseInt(userUpdate.userId)) {
        user.userName = userUpdate.userName;
        user.userAge = userUpdate.userAge;
      }
    });

    const newListUser = listUsers.slice();
    
    setListUsers(newListUser);
  }

  const handleDeleteUser = (userDelete) => {
    const newListUser = listUsers.filter(user => {
      return user.userId !== userDelete.userId;
    });

    setListUsers(newListUser);
  }

  return (
    <div className='app__container'>
      <UserForm 
        handleAddUser={handleAddUser}
      ></UserForm>
      <UserList 
        listUsers={listUsers}
        handleUpdateUser={handleUpdateUser}
        handleDeleteUser={handleDeleteUser}
      ></UserList>
    </div>
  );
}

export default App;
