export default function Home() {
  const handleLogout = () => {
    localStorage.removeItem('token');
    window.location.href = '/login';
  };

  return (
    <div className="w-full min-h-screen bg-gray-50 flex items-center justify-center">
      <div className="max-w-4xl w-full mx-auto p-8">
        <div className="bg-white overflow-hidden shadow-lg rounded-xl p-8 text-center">
          <h1 className="text-4xl font-bold text-gray-900 mb-8">
            Welcome to Home Page
          </h1>
          
          <p className="text-xl text-gray-600 mb-8">
            You are now logged in to the secure area of the application.
          </p>
          
          <button
            onClick={handleLogout}
            className="inline-flex justify-center py-3 px-6 border border-transparent shadow-sm text-lg font-medium rounded-md text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500"
          >
            Logout
          </button>
        </div>
      </div>
    </div>
  );
}
