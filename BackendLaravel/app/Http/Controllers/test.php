<?php

namespace App\Http\Controllers;

use App\Models\Persons;
use App\Models\Roles;
use App\Models\Users;
use Illuminate\Http\Request;

class test extends Controller
{


	public function testORM()
	{


		$users = Users::all();

		foreach ($users as $user) {

			echo $user->user_name."<br>";

			foreach ($user->persons as $person) {

				echo $person->name." ".$person->surname."<br>";

			}
			

			echo $user->roles->role_name."<br><br>";

		}



		die();
	}
}
