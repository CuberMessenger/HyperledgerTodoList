async function addTask(newTask) {
    let connection = new BusinessNetworkConneciton();
    await connection.connect('admin@todolist-network');

    let assetRegistry = connection.getAssetRegistry('com.smie.todolist');
    let factory = connection.getFactory();
    let task = factory.newResource('com.smie.todolist', 'Task', newTask.tid);
    task.title = newTask.title;
    task.todo = newTask.todo;
    task.start = newTask.start;
    task.ddl = newTask.ddl;
    task.owner = newTask.owner;

    await assetRegistry.add(task);
    await connection.disconnect();
}