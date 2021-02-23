import React from 'react';
import Axios from '../../apis/Axios';

class EditTask extends React.Component {

    constructor(props) {
        super(props);

        this.state = { 
            id: -1, 
            name: "", 
            employee: "", 
            points: 0 }
    }

    componentDidMount() {
        this.getTaskById(this.props.match.params.id);
    }

    getTaskById(taskId) {
        Axios.get('/tasks/' + taskId)
        .then(res => {
            // handle success
            console.log(res.data)
            this.setState({id: res.data.id, name: res.data.name, employee:res.data.employee, points:res.data.points});
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Unable to get task!');
         });
    }

    edit() {
        var params = {
            'id' : this.state.id,
            'name' : this.state.name,
            'employee' : this.state.employee,
            'points' : this.state.points
        }

        Axios.put('/tasks/' + this.state.id, params)
        .then(res => {
            // handle success
            console.log(res);
            alert('Task successfully changed!');
            this.props.history.push('/tasks');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Unable to change task!');
         });
    }

    nameChange = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            name: value
        }));
    }

    employeeChange = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            employee: value
        }));
    }

    pointsChange = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            points: value
        }));
    }

    render () {
        return (
            <div>
                <h1>Edit task</h1>
                <label htmlFor="tName">Name</label><br/>
                <input id="tName" type="text" value={this.state.name} onChange={(e) => this.nameChange(e)}/><br/>
                <label htmlFor="tEmployee">Employee</label><br/>
                <input id="tEmployee" type="text" value={this.state.employee} onChange={(e) => this.employeeChange(e)}/><br/>
                <label htmlFor="tPoints">Points</label><br/>
                <input id="tPoints" type="number" value={this.state.points} onChange={(e) => this.pointsChange(e)}/><br/>

                <button className="btn btn-primary" onClick={() => this.edit()}>Edit</button>
            </div>
        )
    }
}

export default EditTask;